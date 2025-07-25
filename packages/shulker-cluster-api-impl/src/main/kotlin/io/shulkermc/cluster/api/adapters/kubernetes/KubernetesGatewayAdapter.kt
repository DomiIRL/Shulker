package io.shulkermc.cluster.api.adapters.kubernetes

import io.shulkermc.cluster.api.adapters.kubernetes.models.AgonesV1GameServer
import java.net.InetSocketAddress
import java.util.Optional
import java.util.concurrent.CompletionStage

enum class WatchAction {
    ADDED,
    MODIFIED,
    DELETED,
}

interface KubernetesGatewayAdapter {
    fun destroy()

    fun asProxy(): SideProxy

    fun listMinecraftServers(): AgonesV1GameServer.List

    fun watchProxyEvents(
        callback: (action: WatchAction, proxy: AgonesV1GameServer) -> Unit,
    ): CompletionStage<EventWatcher>

    fun watchMinecraftServerEvents(
        callback: (action: WatchAction, minecraftServer: AgonesV1GameServer) -> Unit,
    ): CompletionStage<EventWatcher>

    interface SideProxy {
        fun getExternalAddress(): Optional<InetSocketAddress>

        fun watchExternalAddressUpdates(
            callback: (address: Optional<InetSocketAddress>) -> Unit,
        ): CompletionStage<EventWatcher>
    }

    interface EventWatcher {
        fun stop()
    }
}
