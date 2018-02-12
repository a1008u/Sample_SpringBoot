package com.example.sample_6_restapi.config

import org.springframework.boot.actuate.endpoint.Endpoint
import org.springframework.stereotype.Component

/**
 * オリジナルのエンドポイントを作成する
 */
@Component
class CustomEndpoint: Endpoint<String> {
    override fun isEnabled(): Boolean = true

    override fun isSensitive(): Boolean = false

    override fun getId(): String = "custom"

    override fun invoke(): String = "Successful EndPoint test"

}