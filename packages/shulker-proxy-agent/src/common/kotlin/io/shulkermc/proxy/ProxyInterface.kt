package io.shulkermc.proxy

import io.shulkermc.proxy.platform.HookPostOrder
import io.shulkermc.proxy.platform.PlayerDisconnectHook
import io.shulkermc.proxy.platform.PlayerLoginHook
import io.shulkermc.proxy.platform.PlayerPreLoginHook
import io.shulkermc.proxy.platform.ProxyPingHook
import io.shulkermc.proxy.platform.ServerPostConnectHook
import io.shulkermc.proxy.platform.ServerPreConnectHook
import java.net.InetSocketAddress
import java.util.UUID
import java.util.concurrent.TimeUnit

@Suppress("TooManyFunctions")
interface ProxyInterface {
    fun registerServer(
        name: String,
        address: InetSocketAddress,
    )

    fun unregisterServer(name: String): Boolean

    fun hasServer(name: String): Boolean

    fun addProxyPingHook(
        hook: ProxyPingHook,
        postOrder: HookPostOrder,
    )

    fun addPlayerPreLoginHook(
        hook: PlayerPreLoginHook,
        postOrder: HookPostOrder,
    )

    fun addPlayerLoginHook(
        hook: PlayerLoginHook,
        postOrder: HookPostOrder,
    )

    fun addPlayerDisconnectHook(
        hook: PlayerDisconnectHook,
        postOrder: HookPostOrder,
    )

    fun addServerPreConnectHook(
        hook: ServerPreConnectHook,
        postOrder: HookPostOrder,
    )

    fun addServerPostConnectHook(
        hook: ServerPostConnectHook,
        postOrder: HookPostOrder,
    )

    fun prepareNetworkAdminsPermissions(playerIds: List<UUID>)

    fun teleportPlayerOnServer(
        playerId: UUID,
        serverName: String,
    )

    fun getPlayerCount(): Int

    fun getPlayerCapacity(): Int

    fun transferPlayerToAddress(
        playerId: UUID,
        address: InetSocketAddress,
    )

    fun transferEveryoneToAddress(address: InetSocketAddress)

    fun scheduleDelayedTask(
        delay: Long,
        timeUnit: TimeUnit,
        runnable: Runnable,
    ): ScheduledTask

    fun scheduleRepeatingTask(
        delay: Long,
        interval: Long,
        timeUnit: TimeUnit,
        runnable: Runnable,
    ): ScheduledTask

    interface ScheduledTask {
        fun cancel()
    }
}
