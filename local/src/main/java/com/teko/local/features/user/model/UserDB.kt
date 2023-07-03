package com.teko.local.features.user.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teko.domain.User

@Entity(tableName = UserDB.USER_TABLE_NAME)
data class UserDB(
    @PrimaryKey
    val uid: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "is_commercial")
    val isCommercial: Boolean,
    @ColumnInfo(name = "photo")
    val photo: String,
    @ColumnInfo(name = "details")
    val details: DetailsDB,
    @ColumnInfo(name = "assistant_tools")
    val assistantTools: AssistantToolsDB,
    @ColumnInfo(name = "is_training")
    val isTraining: Boolean
) {
    data class DetailsDB(
        @ColumnInfo(name = "phone")
        val phone: String,
        @ColumnInfo(name = "address")
        val address: AddressDB,
        @ColumnInfo(name = "credentials")
        val credentials: CredentialsDB,
        @ColumnInfo(name = "preference")
        val preference: PreferenceDB
    ) {

        data class AddressDB(
            @ColumnInfo(name = "street")
            val street: String,
            @ColumnInfo(name = "village")
            val village: String,
            @ColumnInfo(name = "landmark")
            val landmark: String,
            @ColumnInfo(name = "citoy")
            val city: String,
            @ColumnInfo(name = "state")
            val state: String,
            @ColumnInfo(name = "complete_address")
            val address: String,
            @ColumnInfo(name = "short_address")
            val shortAddress: String,
            @ColumnInfo(name = "unit_building")
            val unitBuilding: String,
            @ColumnInfo(name = "city_address")
            val cityAddress: String,
            @ColumnInfo(name = "latitude")
            val latitude: String,
            @ColumnInfo(name = "longitude")
            val longitude: String
        ) {
            companion object {
                fun empty(): AddressDB {
                    return AddressDB(
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

                fun fromDomain(address: User.Details.Address): AddressDB {
                    return with(address) {
                        AddressDB(
                            street = street,
                            village = village,
                            landmark = landmark,
                            city = city,
                            state = state,
                            address = this.address,
                            shortAddress = shortAddress,
                            unitBuilding = unitBuilding,
                            cityAddress = cityAddress,
                            latitude = latitude,
                            longitude = longitude
                        )
                    }
                }

                fun toDomain(address: AddressDB): User.Details.Address {
                    return with(address) {
                        User.Details.Address(
                            street = street,
                            village = village,
                            landmark = landmark,
                            city = city,
                            state = state,
                            address = this.address,
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

        data class CredentialsDB(
            @ColumnInfo(name = "years")
            val years: String,
            @ColumnInfo(name = "title")
            val title: List<String>,
            @ColumnInfo(name = "certifications")
            val certifications: List<String>,
            @ColumnInfo(name = "security")
            val security: List<String>
        ) {
            companion object {
                fun empty(): CredentialsDB {
                    return CredentialsDB(
                        years = "",
                        title = emptyList(),
                        certifications = emptyList(),
                        security = emptyList()
                    )
                }

                fun fromDomain(credentials: User.Details.Credentials): CredentialsDB {
                    return with(credentials) {
                        CredentialsDB(
                            years = years,
                            title = title,
                            certifications = certifications,
                            security = security
                        )
                    }
                }

                fun toDomain(credentials: CredentialsDB): User.Details.Credentials {
                    return with(credentials) {
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

        data class PreferenceDB(
            @ColumnInfo(name = "transportation")
            val transportation: String,
            @ColumnInfo(name = "appliance_brands")
            val applianceBrands: List<String>,
            @ColumnInfo(name = "appliance_expertise")
            val applianceExpertise: List<String>,
            @ColumnInfo(name = "service_type")
            val serviceType: List<String>,
            @ColumnInfo(name = "service_areas")
            val serviceAreas: List<String>,
            @ColumnInfo(name = "vatp")
            val vatp: Double,
            @ColumnInfo(name = "excluded")
            val excluded: List<String>
        ) {
            companion object {
                fun empty(): PreferenceDB {
                    return PreferenceDB(
                        transportation = "",
                        applianceBrands = emptyList(),
                        applianceExpertise = emptyList(),
                        serviceType = emptyList(),
                        serviceAreas = emptyList(),
                        vatp = 0.0,
                        excluded = emptyList()
                    )
                }

                fun fromDomain(preference: User.Details.Preference): PreferenceDB {
                    return with(preference) {
                        PreferenceDB(
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

                fun toDomain(preference: PreferenceDB): User.Details.Preference {
                    return with(preference) {
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
            fun empty(): DetailsDB {
                return DetailsDB(
                    phone = "",
                    address = AddressDB.empty(),
                    credentials = CredentialsDB.empty(),
                    preference = PreferenceDB.empty()
                )
            }

            fun fromDomain(details: User.Details): DetailsDB {
                return with(details) {
                    DetailsDB(
                        phone = phone,
                        address = AddressDB.fromDomain(address),
                        credentials = CredentialsDB.fromDomain(credentials),
                        preference = PreferenceDB.fromDomain(preference)
                    )
                }
            }

            fun toDomain(details: DetailsDB): User.Details {
                return with(details) {
                    User.Details(
                        phone = phone,
                        address = AddressDB.toDomain(address),
                        credentials = CredentialsDB.toDomain(credentials),
                        preference = PreferenceDB.toDomain(preference)
                    )
                }
            }
        }
    }

    data class AssistantToolsDB(
        @ColumnInfo(name = "assistants")
        val assistants: List<String>,
        @ColumnInfo(name = "tools")
        val tools: List<String>
    ) {
        companion object {
            fun empty(): AssistantToolsDB {
                return AssistantToolsDB(
                    assistants = emptyList(),
                    tools = emptyList()
                )
            }

            fun fromDomain(assistantTools: User.AssistantTools): AssistantToolsDB {
                return with(assistantTools) {
                    AssistantToolsDB(
                        assistants = assistants,
                        tools = tools
                    )
                }
            }

            fun toDomain(assistantTools: AssistantToolsDB): User.AssistantTools {
                return with(assistantTools) {
                    User.AssistantTools(
                        assistants = assistants,
                        tools = tools
                    )
                }
            }
        }
    }

    companion object {
        const val USER_TABLE_NAME = "user"

        fun fromDomain(user: User): UserDB {
            return with(user) {
                UserDB(
                    uid = uid,
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    type = type,
                    rating = rating,
                    isCommercial = isCommercial,
                    photo = photo,
                    details = DetailsDB.fromDomain(details),
                    assistantTools = AssistantToolsDB.fromDomain(user.assistantTools),
                    isTraining = isTraining
                )
            }
        }

        fun empty(): UserDB {
            return UserDB(
                uid = "",
                email = "",
                firstName = "",
                lastName = "",
                type = "",
                rating = "",
                isCommercial = false,
                photo = "",
                details = DetailsDB.empty(),
                assistantTools = AssistantToolsDB.empty(),
                isTraining = false
            )
        }

        fun toDomain(userDB: UserDB): User {
            return with(userDB) {
                User(
                    uid = uid,
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    type = type,
                    rating = rating,
                    isCommercial = isCommercial,
                    photo = photo,
                    details = DetailsDB.toDomain(details),
                    assistantTools = AssistantToolsDB.toDomain(assistantTools),
                    isTraining = isTraining
                )
            }
        }
    }
}
