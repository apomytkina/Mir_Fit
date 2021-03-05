package ru.hse.project.clientmir.clientAuth

import android.annotation.SuppressLint
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

class Http {

    companion object {
        val SSL: String = "SSL"
        val InsecureHttpClient: OkHttpClient = insecureOkHttpClient()

        public fun client(): OkHttpClient {
            return InsecureHttpClient;
        }

        private fun insecureOkHttpClient(): OkHttpClient {
            val trustAllCerts: Array<TrustManager> = arrayOf(trustManager());
            val sslContext: SSLContext = SSLContext.getInstance(SSL)
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(hostnameVerifier())
            return builder.build()
        }


        private fun trustManager(): TrustManager {
            return object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        }

        private fun hostnameVerifier(): HostnameVerifier {
            return HostnameVerifier { p0, p1 -> true }
        }
    }

}