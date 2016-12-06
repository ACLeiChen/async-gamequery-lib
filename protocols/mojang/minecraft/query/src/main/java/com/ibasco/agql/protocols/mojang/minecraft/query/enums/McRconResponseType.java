/*
 *   MIT License
 *
 *   Copyright (c) 2016 Asynchronous Game Query Library
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package com.ibasco.agql.protocols.mojang.minecraft.query.enums;

import java.util.Arrays;

public enum McRconResponseType {
    AUTH(2),
    COMMAND(0),
    TERMINATOR(-1);

    private int header;

    McRconResponseType(int header) {
        this.header = header;
    }

    public static McRconResponseType get(int header) {
        return Arrays.stream(values()).filter(res -> res.getHeader() == header).findFirst().orElse(null);
    }

    public static boolean isValid(int header) {
        return get(header) != null;
    }

    public int getHeader() {
        return header;
    }
}
