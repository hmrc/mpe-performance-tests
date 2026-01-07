/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.mpe

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.{HttpConfiguration, ServicesConfiguration}
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.core.session.Expression

import scala.util.Random


object MPERequests extends HttpConfiguration with ServicesConfiguration {

  val baseurl: String = baseUrlFor("base-url")

  val route: String = "members-protections-and-enhancements"

  val authLoginstubRoot: String = baseUrlFor("auth-login-stub")

  val loginUrl: String = authLoginstubRoot + "/auth-login-stub/gg-sign-in?continue=/members-protections-and-enhancements"

  val dashboardUrl: String = baseurl + "/members-protections-and-enhancements/dashboard"

  val startPageUrl: String = baseurl + "//members-protections-and-enhancements/start"

  val memberDetailsPageUrl: String = baseurl + "//members-protections-and-enhancements/members-name"

  val memberDOBPageUrl: String = baseurl + "//members-protections-and-enhancements/members-date-of-birth"

  val memberNINOPageUrl: String = baseurl + "//members-protections-and-enhancements/members-national-insurance-number"

  val memberPSACheckRefPageUrl: String = baseurl + "//members-protections-and-enhancements/members-pension-scheme-administrator-check-reference"

  val cyaPageUrl: String = baseurl + "//members-protections-and-enhancements/check-your-answers"

  val resultsPageUrl: String = baseurl + "//members-protections-and-enhancements/results"

  val noResultsPageUrl: String = baseurl + "//members-protections-and-enhancements/no-results"

  val defaultErrorPageUrl: String = baseurl + "//members-protections-and-enhancements/no-results"

  val CsrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  def saveCsrfToken(): CheckBuilder[RegexCheckType, String] = regex(_ => CsrfPattern).saveAs("csrfToken")

  val csrfToken: Expression[String] = "#{csrfToken}"

  def generateNino(prefix: String = "AA"): String = {
    val num = Random.nextInt(1000000)
    val suffix = "A"
    val str: String = Random.alphanumeric.filter(_.isLetter).take(2).map(_.toUpper).mkString

    prefix + f"$str$num%06d$suffix".drop(prefix.length)
  }

  val testNinoResultsPage: String =  generateNino("NW9")

  val testNinoNoResultsPage: String =  generateNino("EC1")

  val testNinoDefaultErrorPage: String =  generateNino("ES50")

  def generateCheckRef(prefix: String = "PSA678"): String = {
    val num = Random.nextInt(100000000)
    val str: Char = Random.alphanumeric.filter(_.isLetter).head.toUpper
    prefix + f"PSA$num%08d$str".drop(prefix.length)
  }

  val psaCheckRef: String = generateCheckRef()

  def getLogin: HttpRequestBuilder = {
    http("get Login Details")
      .get(loginUrl)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  }

  def postLogin: HttpRequestBuilder = {
    http("Post Login Details")
      .post(loginUrl)
      .formParam("csrfToken", _("csrfToken").as[String])
      .formParam("authorityId", _ => "")
      .formParam("gatewayToken",_ => "")
      .formParam("redirectionUrl",_ => startPageUrl)
      .formParam("credentialStrength",_ => "strong")
      .formParam("confidenceLevel",_ => "50")
      .formParam("affinityGroup",_ => "Agent")
      .formParam("usersName",_ => "")
      .formParam("email",_ => "user@test.com")
      .formParam("credentialRole",_ => "User")
      .formParam("nino",_ => "")
      .formParam("groupIdentifier",_ => "")
      .formParam("agent.agentId",_ => "")
      .formParam("agent.agentCode",_ => "")
      .formParam("agent.agentFriendlyName",_ => "")
      .formParam("unreadMessageCount",_ => "")
      .formParam("mdtp.sessionId",_ => "")
      .formParam("mdtp.deviceId",_ => "")
      .formParam("presets-dropdown",_ => "SA")
      .formParam("enrolment[0].name",_ => "")
      .formParam("enrolment[0].taxIdentifier[0].name",_ => "")
      .formParam("enrolment[0].taxIdentifier[0].value",_ => "")
      .formParam("enrolment[0].state",_ => "Activated")
      .formParam("enrolment[1].name",_ => "")
      .formParam("enrolment[1].taxIdentifier[0].name",_ => "")
      .formParam("enrolment[1].taxIdentifier[0].value",_ => "")
      .formParam("enrolment[1].state",_ => "Activated")
      .formParam("enrolment[2].name",_ => "")
      .formParam("enrolment[2].taxIdentifier[0].name",_ => "")
      .formParam("enrolment[2].taxIdentifier[0].value",_ => "")
      .formParam("enrolment[2].state",_ => "Activated")
      .formParam("enrolment[3].name",_ => "")
      .formParam("enrolment[3].taxIdentifier[0].name",_ => "")
      .formParam("enrolment[3].taxIdentifier[0].value",_ => "")
      .formParam("enrolment[3].state",_ => "Activated")
      .formParam("enrolment[4].name",_ => "HMRC-PODS-ORG")
      .formParam("enrolment[4].taxIdentifier[0].name",_ => "PSAID")
      .formParam("enrolment[4].taxIdentifier[0].value",_ => "A2100001")
      .formParam("enrolment[4].state",_ => "Activated")
      .formParam("itmp.givenName",_ => "")
      .formParam("itmp.middleName",_ => "")
      .formParam("itmp.familyName",_ => "")
      .formParam("itmp.dateOfBirth",_ => "")
      .formParam("itmp.address.line1",_ => "")
      .formParam("itmp.address.line2",_ => "")
      .formParam("itmp.address.line3",_ => "")
      .formParam("itmp.address.line4",_ => "")
      .formParam("itmp.address.line5",_ => "")
      .formParam("itmp.address.postCode",_ => "")
      .formParam("itmp.address.countryName",_ => "")
      .formParam("itmp.address.countryCode",_ => "")
      .check(status.is(303))
  }

  def getStartPage: HttpRequestBuilder = {
    http("Get Start Page")
      .get(startPageUrl: String)
      .check(status.is(200))
  }

  def getMemberDetailsPage: HttpRequestBuilder = {
    http("Get Members Details Page")
      .get(memberDetailsPageUrl: String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  }

  def postMemberDetailsPage: HttpRequestBuilder = {
    http("Post to Member-Details Page")
      .post(memberDetailsPageUrl: String)
      .formParam("csrfToken", _("csrfToken").as[String])
      .formParam("firstName",_=> "Pfirstnamel")
      .formParam("lastName",_=> "Blastnamen")
      .check(status.is(303))
  }

  def getMemberDOBPage: HttpRequestBuilder = {
    http("Get Members DOB Page")
      .get(memberDOBPageUrl)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  }

  def postMemberDOBPage: HttpRequestBuilder = {
    http("Post to Member DOB Page")
      .post(memberDOBPageUrl)
      .formParam("csrfToken", _("csrfToken").as[String])
      .formParam("dateOfBirth.day",_ => "2")
      .formParam("dateOfBirth.month",_ => "12")
      .formParam("dateOfBirth.year",_ => "1939")
      .check(status.is(303))
  }

  def getMemberNINOPage: HttpRequestBuilder = {
    http("Get Members NINO Page")
      .get(memberNINOPageUrl)
      .check(status.is(200))
  }

  def postMemberNINOPage: HttpRequestBuilder = {
    http("Post to Member NINO Page")
      .post(memberNINOPageUrl)
      .formParam("csrfToken", _("csrfToken").as[String])
      .formParam("nino",_ => testNinoResultsPage)
      .check(status.is(303))
  }

  def postMemberNINONoResultsPage: HttpRequestBuilder = {
    http("Post to Member NINO Page")
      .post(memberNINOPageUrl)
      .formParam("csrfToken", _("csrfToken").as[String])
      .formParam("nino",_ => testNinoNoResultsPage)
      .check(status.is(303))
  }

  def postMemberNINODefaultErrorPage: HttpRequestBuilder = {
    http("Post to Member NINO Page")
      .post(memberNINOPageUrl)
      .formParam("csrfToken", _("csrfToken").as[String])
      .formParam("nino",_ => testNinoDefaultErrorPage)
      .check(status.is(303))
  }

  def getMemberPSACheckRefPage: HttpRequestBuilder = {
    http("Get Members PSA Check Ref Page")
      .get(memberPSACheckRefPageUrl)
      .check(status.is(200))
  }

  def postMemberPSACheckRefPage: HttpRequestBuilder = {
    http("Post to Member PSA Check Ref Page")
      .post(memberPSACheckRefPageUrl)
      .formParam("csrfToken", _("csrfToken").as[String])
      .formParam("psaCheckRef",_ => psaCheckRef)
      .check(status.is(303))
  }

  def getCYAPage: HttpRequestBuilder = {
    http("Get Check Your Answers Page")
      .get(cyaPageUrl)
      .check(status.is(200))
  }

  def postCYAPage: HttpRequestBuilder = {
    http("Post Check Your Answers Page")
      .post(cyaPageUrl)
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))
  }

  def getResultsPage: HttpRequestBuilder = {
    http("Get Results Page")
      .get(resultsPageUrl)
      .check(status.is(200))
  }

  def getNoResultsPage: HttpRequestBuilder = {
    http("Get Results Page")
      .get(noResultsPageUrl)
      .check(status.is(200))
  }

  def getDefaultErrorPage: HttpRequestBuilder = {
    http("Get Default Error Page")
      .get(defaultErrorPageUrl)
      .check(status.is(200))
  }
}

