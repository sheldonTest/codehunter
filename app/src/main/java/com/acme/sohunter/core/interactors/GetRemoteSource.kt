package com.acme.sohunter.core.interactors

import com.acme.sohunter.core.data.Repository

class GetRemoteSource(private val repository: Repository) {

    operator fun invoke() = repository.remoteSource()
}