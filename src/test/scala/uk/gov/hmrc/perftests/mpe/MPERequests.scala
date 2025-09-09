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
import io.gatling.core.check.css.CssCheckType
import jodd.lagarto.dom.NodeSelector


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

  def saveCsrfToken: CheckBuilder[CssCheckType, NodeSelector, String] = css("input[name='csrfToken']", "value").optional.saveAs("csrfToken")

  def getLogin: HttpRequestBuilder = {
    http("get Login Details")
      .get(loginUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postLogin: HttpRequestBuilder = {
    http("Post Login Details")
      .post(loginUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("authorityId", "")
      .formParam("gatewayToken", "")
      .formParam("redirectionUrl", startPageUrl)
      .formParam("credentialStrength", "strong")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Agent")
      .formParam("usersName", "")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("nino", "")
      .formParam("groupIdentifier", "")
      .formParam("agent.agentId", "")
      .formParam("agent.agentCode", "")
      .formParam("agent.agentFriendlyName", "")
      .formParam("unreadMessageCount", "")
      .formParam("mdtp.sessionId", "")
      .formParam("mdtp.deviceId", "")
      .formParam("presets-dropdown", "SA")
      .formParam("enrolment[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].value", "")
      .formParam("enrolment[0].state", "Activated")
      .formParam("enrolment[1].name", "")
      .formParam("enrolment[1].taxIdentifier[0].name", "")
      .formParam("enrolment[1].taxIdentifier[0].value", "")
      .formParam("enrolment[1].state", "Activated")
      .formParam("enrolment[2].name", "")
      .formParam("enrolment[2].taxIdentifier[0].name", "")
      .formParam("enrolment[2].taxIdentifier[0].value", "")
      .formParam("enrolment[2].state", "Activated")
      .formParam("enrolment[3].name", "")
      .formParam("enrolment[3].taxIdentifier[0].name", "")
      .formParam("enrolment[3].taxIdentifier[0].value", "")
      .formParam("enrolment[3].state", "Activated")
      .formParam("enrolment[4].name", "HMRC-PODS-ORG")
      .formParam("enrolment[4].taxIdentifier[0].name", "PSAID")
      .formParam("enrolment[4].taxIdentifier[0].value", "A2100001")
      .formParam("enrolment[4].state", "Activated")
      .formParam("itmp.givenName", "")
      .formParam("itmp.middleName", "")
      .formParam("itmp.familyName", "")
      .formParam("itmp.dateOfBirth", "")
      .formParam("itmp.address.line1", "")
      .formParam("itmp.address.line2", "")
      .formParam("itmp.address.line3", "")
      .formParam("itmp.address.line4", "")
      .formParam("itmp.address.line5", "")
      .formParam("itmp.address.postCode", "")
      .formParam("itmp.address.countryName", "")
      .formParam("itmp.address.countryCode", "")
      .check(status.is(303))
  }

  def getStartPage: HttpRequestBuilder = {
    http("Get Start Page")
      .get(startPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def getMemberDetailsPage: HttpRequestBuilder = {
    http("Get Members Details Page")
      .get(memberDetailsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postMemberDetailsPage: HttpRequestBuilder = {
    http("Post to Member-Details Page")
      .post(memberDetailsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("firstName", "Pearl Turner")
      .formParam("lastName", "Harvey")
      .check(status.is(303))
  }

  def getMemberDOBPage: HttpRequestBuilder = {
    http("Get Members DOB Page")
      .get(memberDOBPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postMemberDOBPage: HttpRequestBuilder = {
    http("Post to Member DOB Page")
      .post(memberDOBPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("dateOfBirth.day", "27")
      .formParam("dateOfBirth.month", "03")
      .formParam("dateOfBirth.year", "2007")
      .check(status.is(303))
  }

  def getMemberNINOPage: HttpRequestBuilder = {
    http("Get Members NINO Page")
      .get(memberNINOPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postMemberNINOPage: HttpRequestBuilder = {
    http("Post to Member NINO Page")
      .post(memberNINOPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("nino", "NW 99 99 99 C")
      .check(status.is(303))
  }

  def postMemberNINONoResultsPage: HttpRequestBuilder = {
    http("Post to Member NINO Page")
      .post(memberNINOPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("nino", "EC 13 05 89 A")
      .check(status.is(303))
  }

  def postMemberNINODefaultErrorPage: HttpRequestBuilder = {
    http("Post to Member NINO Page")
      .post(memberNINOPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("nino", "AA 50 05 00 A")
      .check(status.is(303))
  }

  def getMemberPSACheckRefPage: HttpRequestBuilder = {
    http("Get Members PSA Check Ref Page")
      .get(memberPSACheckRefPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postMemberPSACheckRefPage: HttpRequestBuilder = {
    http("Post to Member PSA Check Ref Page")
      .post(memberPSACheckRefPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("psaCheckRef", "PSA 12 34 56 78 W")
      .check(status.is(303))
  }

  def getCYAPage: HttpRequestBuilder = {
    http("Get Check Your Answers Page")
      .get(cyaPageUrl)
      .check(status.is(200))
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
    http("Get Results Page")
      .get(noResultsPageUrl)
      .check(status.is(200))
  }
}

