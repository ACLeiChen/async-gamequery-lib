/***************************************************************************************************
 * MIT License
 *
 * Copyright (c) 2016 Rafael Luis Ibasco
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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 **************************************************************************************************/

package org.ribasco.agql.protocols.valve.source.query;

import io.netty.channel.Channel;
import org.ribasco.agql.core.transport.NettyChannelInitializer;
import org.ribasco.agql.core.transport.NettyTransport;
import org.ribasco.agql.core.transport.handlers.ErrorHandler;
import org.ribasco.agql.protocols.valve.source.query.handlers.SourceRconPacketAssembler;
import org.ribasco.agql.protocols.valve.source.query.handlers.SourceRconPacketDecoder;
import org.ribasco.agql.protocols.valve.source.query.handlers.SourceRconRequestEncoder;

import java.util.function.BiConsumer;

class SourceRconChannelInitializer implements NettyChannelInitializer {

    private BiConsumer<SourceRconResponse, Throwable> responseHandler;

    SourceRconChannelInitializer(BiConsumer<SourceRconResponse, Throwable> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void initializeChannel(Channel channel, NettyTransport transport) {
        SourceRconPacketBuilder rconBuilder = new SourceRconPacketBuilder(transport.getAllocator());
        channel.pipeline().addLast(new ErrorHandler());
        channel.pipeline().addLast(new SourceRconRequestEncoder(rconBuilder));
        channel.pipeline().addLast(new SourceRconPacketAssembler());
        channel.pipeline().addLast(new SourceRconPacketDecoder(rconBuilder, responseHandler));
    }
}
