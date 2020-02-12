package org.jesperancinha.concerts.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import javax.validation.constraints.NotEmpty


@ConfigurationProperties(prefix = "r2dbc")
class ConfigurationProperties {
    private var url: @NotEmpty String? = null

    private var user: String? = null
    private var password: String? = null

    /**
     * @return the url
     */
    fun getUrl(): String? {
        return url
    }

    /**
     * @param url the url to set
     */
    fun setUrl(url: String?) {
        this.url = url
    }

    /**
     * @return the user
     */
    fun getUser(): String? {
        return user
    }

    /**
     * @param user the user to set
     */
    fun setUser(user: String?) {
        this.user = user
    }

    /**
     * @return the password
     */
    fun getPassword(): String? {
        return password
    }

    /**
     * @param password the password to set
     */
    fun setPassword(password: String?) {
        this.password = password
    }
}
