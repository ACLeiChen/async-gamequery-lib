/*
 * MIT License
 *
 * Copyright (c) 2016 Asynchronous Game Query Library
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ibasco.agql.protocols.valve.steam.master;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>A Master Server Filter Utility class</p>
 */
public final class MasterServerFilter {
    private StringBuffer filter;

    /**
     * Default constructor
     */
    private MasterServerFilter() {
        filter = new StringBuffer();
    }

    /**
     * <p>A factory method to create a new {@link MasterServerFilter} instance</p>
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public static MasterServerFilter create() {
        return new MasterServerFilter();
    }

    /**
     * A filter to return all available servers
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter allServers() {
        filter.setLength(0);
        return this;
    }

    /**
     * <p>Servers that are spectator proxies</p>
     *
     * @param value
     *         Set to True to filter spectator proxy servers
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter isSpecProxy(Boolean value) {
        return create("proxy", value);
    }

    /**
     * <p>Servers that are not full</p>
     *
     * @param value
     *         Set to True to filter servers that are full
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter isFull(Boolean value) {
        return create("full", value);
    }

    /**
     * <p>Servers that are not empty</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter isEmpty(Boolean value) {
        return create("empty", value);
    }

    /**
     * <p>Filter for password protected servers</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter isPasswordProtected(Boolean value) {
        return create("password", value);
    }

    /**
     * <p>Servers running on a Linux platform</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter isLinuxServer(Boolean value) {
        return create("linux", value);
    }

    /**
     * <p>Servers running the specified map (ex. cs_italy)</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter mapName(String value) {
        return create("map", value);
    }

    /**
     * <p>Servers running the specified modification (ex. cstrike)</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter gamedir(String value) {
        return create("gamedir", value);
    }

    /**
     * <p>Servers using anti-cheat technology (VAC, but potentially others as well)</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter isSecure(Boolean value) {
        return create("secure", value);
    }

    /**
     * <p>Servers running dedicated</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter dedicated(Boolean value) {
        return create("dedicated", value);
    }

    /**
     * <p>A special filter, specifies that servers matching all of the following [x] conditions should not be
     * returned</p>
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter nand() {
        return create("nand", "");
    }

    /**
     * <p>A special filter, specifies that servers matching any of the following [x] conditions should not be
     * returned</p>
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter nor() {
        return create("nor", "");
    }

    /**
     * <p>Servers that are NOT running game [appid] (This was introduced to block Left 4 Dead games from the Steam
     * Server Browser)</p>
     *
     * @param appId
     *         An integer representing the appId of a game
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter napp(Integer appId) {
        if (appId != null && appId > 0)
            return create("napp", appId);
        return this;
    }

    /**
     * <p>Servers that are empty</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter hasNoPlayers(Boolean value) {
        return create("noplayers", value);
    }

    /**
     * <p>Servers with all of the given tag(s) in sv_tags</p>
     *
     * @param tags
     *         A {@link String} array of tags
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter gametypes(String... tags) {
        return create("gametype", StringUtils.join(tags, ","));
    }

    /**
     * Servers with ALL of the given tag(s) in their 'hidden' tags (e.g. L4D2)
     *
     * @param tags
     *         Array of String game logger tags
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter gamedata(String... tags) {
        return create("gamedata", StringUtils.join(tags, ","));
    }

    /**
     * Servers with ANY of the given tag(s) in their 'hidden' tags (e.g. L4D2)
     *
     * @param tags
     *         Array of String game logger tags
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter gamedataOr(String... tags) {
        return create("gamedataor", StringUtils.join(tags, ","));
    }

    /**
     * Servers with their hostname matching [hostname]
     *
     * @param nameWildcard
     *         Hostname to lookup (can use * as a wildcard)
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter withHostName(String nameWildcard) {
        return create("name_match", nameWildcard);
    }

    /**
     * Servers running version [version]
     *
     * @param version
     *         Version to search (can use * as a wildcard)
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter hasVersion(String version) {
        return create("version_match", version);
    }

    /**
     * Return only one logger for each unique IP address matched
     *
     * @param value
     *         Set to True to return only one logger for each unique IP
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter onlyOneServerPerUniqueIp(Boolean value) {
        return create("collapse_addr_hash", value);
    }

    /**
     * <p>Return only servers on the specified IP address (port supported and optional)</p>
     *
     * @param ipPort
     *         IP[:port] format
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter hasServerIp(String ipPort) {
        return create("gameaddr", ipPort);
    }

    /**
     * <p>Servers that are whitelisted</p>
     *
     * @param value
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter isWhitelisted(Boolean value) {
        return create("white", value);
    }

    /**
     * <p>Servers that are running game [appid]</p>
     *
     * @param appId
     *         An integer representing the appId of a game
     *
     * @return Instance of {@link MasterServerFilter}
     */
    public MasterServerFilter appId(Integer appId) {
        if (appId > 0)
            return create("appId", appId);
        return this;
    }

    /**
     * A utility method to create a Key-Value string pair
     *
     * @param key
     *         A {@link String} representing the key
     * @param value
     *         A {@link String} representing the value associated with the key
     *
     * @return Instance of {@link MasterServerFilter}
     */
    private MasterServerFilter create(String key, Object value) {
        if (StringUtils.isEmpty(key) || value == null) {
            return this;
        }

        Object tmpValue = value;

        if (tmpValue instanceof Boolean)
            tmpValue = (((Boolean) tmpValue).booleanValue()) ? "1" : "0";

        if (tmpValue != null) {
            if ("and".equals(key) || "or".equals(key) || "nor".equals(key))
                filter.append("\\").append(key);
            else
                filter.append("\\").append(key).append("\\").append(tmpValue);
        }
        return this;
    }

    /**
     * Returns a {@link String} representation of this filter builder instance
     *
     * @return A {@link String}
     */
    @Override
    public String toString() {
        return filter.toString();
    }
}
