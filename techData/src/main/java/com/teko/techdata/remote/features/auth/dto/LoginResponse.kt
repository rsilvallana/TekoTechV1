package com.teko.techdata.remote.features.auth.dto

import com.google.gson.annotations.SerializedName
import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User

data class LoginResponse(
    @SerializedName("uid")
    val uid: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fname")
    val firstName: String?,
    @SerializedName("lname")
    val lastName: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("isCommercial")
    val isCommercial: Boolean?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("details")
    val details: DetailsDTO?,
    @SerializedName("assisttant_tools")
    val assistantTools: AssistantToolsDTO?,
    @SerializedName("isTraining")
    val isTraining: Boolean?
) {
    data class DetailsDTO(
        @SerializedName("phone")
        val phone: String,
        @SerializedName("address")
        val address: AddressDTO,
        @SerializedName("creds")
        val credentials: CredentialsDTO,
        @SerializedName("prefs")
        val preference: PreferenceDTO
    ) {

        data class AddressDTO(
            @SerializedName("street")
            val street: String,
            @SerializedName("village")
            val village: String,
            @SerializedName("landmark")
            val landmark: String,
            @SerializedName("city")
            val city: String,
            @SerializedName("state")
            val state: String,
            @SerializedName("address")
            val address: String,
            @SerializedName("shortAddress")
            val shortAddress: String,
            @SerializedName("unitBuilding")
            val unitBuilding: String,
            @SerializedName("cityAddress")
            val cityAddress: String,
            @SerializedName("latitude")
            val latitude: String,
            @SerializedName("longitude")
            val longitude: String
        ) {
            companion object {
                fun empty(): AddressDTO {
                    return AddressDTO(
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

                fun toDomain(dto: AddressDTO): User.Details.Address {
                    return with(dto) {
                        User.Details.Address(
                            street = street,
                            village = village,
                            landmark = landmark,
                            city = city,
                            state = state,
                            address = address,
                            shortAddress = shortAddress,
                            unitBuilding = unitBuilding,
                            cityAddress = cityAddress,
                            latitude = latitude,
                            longitude = longitude
                        )
                    }
                }
            }
        }

        data class CredentialsDTO(
            @SerializedName("years")
            val years: String,
            @SerializedName("title")
            val title: List<String>,
            @SerializedName("certifications")
            val certifications: List<String>,
            @SerializedName("security")
            val security: List<String>
        ) {
            companion object {
                fun empty(): CredentialsDTO {
                    return CredentialsDTO(
                        years = "",
                        title = emptyList(),
                        certifications = emptyList(),
                        security = emptyList()
                    )
                }

                fun toDomain(dto: CredentialsDTO): User.Details.Credentials {
                    return with(dto) {
                        User.Details.Credentials(
                            years = years,
                            title = title,
                            certifications = certifications,
                            security = security
                        )
                    }
                }
            }
        }

        data class PreferenceDTO(
            @SerializedName("transportation")
            val transportation: String,
            @SerializedName("applianceBrands")
            val applianceBrands: List<String>,
            @SerializedName("applianceExpertise")
            val applianceExpertise: List<String>,
            @SerializedName("serviceType")
            val serviceType: List<String>,
            @SerializedName("serviceAreas")
            val serviceAreas: List<String>,
            @SerializedName("vatp")
            val vatp: Double,
            @SerializedName("excluded")
            val excluded: List<String>
        ) {
            companion object {
                fun empty(): PreferenceDTO {
                    return PreferenceDTO(
                        transportation = "",
                        applianceBrands = emptyList(),
                        applianceExpertise = emptyList(),
                        serviceType = emptyList(),
                        serviceAreas = emptyList(),
                        vatp = 0.0,
                        excluded = emptyList()
                    )
                }

                fun toDomain(dto: PreferenceDTO): User.Details.Preference {
                    return with(dto) {
                        User.Details.Preference(
                            transportation = transportation,
                            applianceBrands = applianceBrands,
                            applianceExpertise = applianceExpertise,
                            serviceType = serviceType,
                            serviceAreas = serviceAreas,
                            vatp = vatp,
                            excluded = excluded
                        )
                    }
                }
            }
        }

        companion object {
            fun empty(): DetailsDTO {
                return DetailsDTO(
                    phone = "",
                    address = AddressDTO.empty(),
                    credentials = CredentialsDTO.empty(),
                    preference = PreferenceDTO.empty()
                )
            }

            fun toDomain(dto: DetailsDTO): User.Details {
                return with(dto) {
                    User.Details(
                        phone = phone,
                        address = AddressDTO.toDomain(address),
                        credentials = CredentialsDTO.toDomain(credentials),
                        preference = PreferenceDTO.toDomain(preference)
                    )
                }
            }
        }
    }

    data class AssistantToolsDTO(
        @SerializedName("assistants")
        val assistants: List<String>,
        @SerializedName("tools")
        val tools: List<String>
    ) {
        companion object {
            fun empty(): AssistantToolsDTO {
                return AssistantToolsDTO(
                    assistants = emptyList(),
                    tools = emptyList()
                )
            }

            fun toDomain(dto: AssistantToolsDTO): User.AssistantTools {
                return with(dto) {
                    User.AssistantTools(
                        assistants = assistants,
                        tools = tools
                    )
                }
            }
        }
    }

    companion object {
        fun mapLoginResponse(loginResponse: LoginResponse, token: String): Pair<User, AccessToken> {
            return Pair(
                toDomain(loginResponse),
                AccessToken(token)
            )
        }

        fun toDomain(response: LoginResponse): User {
            return with(response) {
                User(
                    uid = uid.orEmpty(),
                    email = email.orEmpty(),
                    firstName = firstName.orEmpty(),
                    lastName = lastName.orEmpty(),
                    type = type.orEmpty(),
                    rating = rating.orEmpty(),
                    isCommercial = isCommercial ?: false,
                    photo = photo.orEmpty(),
                    details = DetailsDTO.toDomain(details ?: DetailsDTO.empty()),
                    assistantTools = AssistantToolsDTO.toDomain(
                        assistantTools ?: AssistantToolsDTO.empty()
                    ),
                    isTraining = isTraining ?: false
                )
            }
        }

        fun empty(): LoginResponse {
            return LoginResponse(
                uid = "",
                email = "",
                firstName = "",
                lastName = "",
                type = "",
                rating = "",
                isCommercial = false,
                photo = "",
                details = DetailsDTO.empty(),
                assistantTools = AssistantToolsDTO.empty(),
                isTraining = false
            )
        }
    }
}
