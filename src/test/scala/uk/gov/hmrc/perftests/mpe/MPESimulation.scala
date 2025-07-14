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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.mpe.MPERequests.{getMemberDOBPage, getMemberDetailsPage, getMemberNINOPage, getMemberPSACheckRefPage, _}

class MPESimulation extends PerformanceTestRunner {

//  setup("home-page", "Home Page") withRequests navigateToHomePage

  setup("members-protection-enhancement-journey-results-page",
    "Check Manage Pension Scheme Protected Allowance for valid member look up") withRequests(
    getLogin,
    postLogin,
    getStartPage,
    getMemberDetailsPage,
    postMemberDetailsPage,
    getMemberDOBPage,
    postMemberDOBPage,
    getMemberNINOPage,
    postMemberNINOPage,
    getMemberPSACheckRefPage,
    postMemberPSACheckRefPage,
    getCYAPage,
    getResultsPage)

  setup("members-protection-enhancement-journey-no-results-page",
    "Check Manage Pension Scheme Protected Allowance for No match") withRequests(
    getLogin,
    postLogin,
    getStartPage,
    getMemberDetailsPage,
    postMemberDetailsPage,
    getMemberDOBPage,
    postMemberDOBPage,
    getMemberNINOPage,
    postMemberNINONoResultsPage,
    getMemberPSACheckRefPage,
    postMemberPSACheckRefPage,
    getCYAPage,
    getNoResultsPage)

  setup("members-protection-enhancement-journey-default-error-page",
    "Check Manage Pension Scheme Protected Allowance for Default Error Page") withRequests(
    getLogin,
    postLogin,
    getStartPage,
    getMemberDetailsPage,
    postMemberDetailsPage,
    getMemberDOBPage,
    postMemberDOBPage,
    getMemberNINOPage,
    postMemberNINODefaultErrorPage,
    getMemberPSACheckRefPage,
    postMemberPSACheckRefPage,
    getCYAPage,
    getResultsPage)

  runSimulation()
}
