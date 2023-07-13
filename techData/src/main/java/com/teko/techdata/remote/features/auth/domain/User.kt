package com.teko.techdata.remote.features.auth.domain

data class User(
    val uid: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val type: String,
    val rating: String,
    val isCommercial: Boolean,
    val photo: String,
    val details: Details,
    val assistantTools: AssistantTools,
    val isTraining: Boolean
) {
    data class Details(
        val phone: String,
        val address: Address,
        val credentials: Credentials,
        val preference: Preference
    ) {

        data class Address(
            val street: String,
            val village: String,
            val landmark: String,
            val city: String,
            val state: String,
            val address: String,
            val shortAddress: String,
            val unitBuilding: String,
            val cityAddress: String,
            val latitude: String,
            val longitude: String
        ) {
            companion object {
                fun empty(): Address {
                    return Address(
                        street = "",
                        village = "",
                        landmark = "",
                        city = "",
                        state = "",
                        address = "",
                        shortAddress = "",
                        unitBuilding = "",
                        cityAddress = "",
                        latitude = "",
                        longitude = ""
                    )
                }
            }
        }

        data class Credentials(
            val years: String,
            val title: List<String>,
            val certifications: List<String>,
            val security: List<String>
        ) {
            companion object {
                fun empty(): Credentials {
                    return Credentials(
                        years = "",
                        title = emptyList(),
                        certifications = emptyList(),
                        security = emptyList()
                    )
                }
            }
        }

        data class Preference(
            val transportation: String,
            val applianceBrands: List<String>,
            val applianceExpertise: List<String>,
            val serviceType: List<String>,
            val serviceAreas: List<String>,
            val vatp: Double,
            val excluded: List<String>
        ) {
            companion object {
                fun empty(): Preference {
                    return Preference(
                        transportation = "",
                        applianceBrands = emptyList(),
                        applianceExpertise = emptyList(),
                        serviceType = emptyList(),
                        serviceAreas = emptyList(),
                        vatp = 0.0,
                        excluded = emptyList()
                    )
                }
            }
        }

        companion object {
            fun empty(): Details {
                return Details(
                    phone = "",
                    address = Address.empty(),
                    credentials = Credentials.empty(),
                    preference = Preference.empty()
                )
            }
        }
    }

    data class AssistantTools(
        val assistants: List<String>,
        val tools: List<String>
    ) {
        companion object {
            fun empty(): AssistantTools {
                return AssistantTools(
                    assistants = emptyList(),
                    tools = emptyList()
                )
            }
        }
    }
}
