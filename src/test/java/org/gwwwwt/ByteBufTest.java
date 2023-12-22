package org.gwwwwt;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;

public class ByteBufTest {

    private static void printByteBufInfo(String message, ByteBuf buffer) {
        System.out.println("after =============" + message + "=============");
        System.out.println("capacity(): " + buffer.capacity());
        System.out.println("maxCapacity(): " + buffer.maxCapacity());
        System.out.println("readerIndex(): " + buffer.readerIndex());
        System.out.println("readableBytes(): " + buffer.readableBytes());
        System.out.println("isReadable(): " + buffer.isReadable());
        System.out.println("writerIndex(): " + buffer.writerIndex());
        System.out.println("writableBytes(): " + buffer.writableBytes());
        System.out.println("isWritable(): " + buffer.isWritable());
        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println();
    }
    @Test
    public void byteBufTest() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        printByteBufInfo("allocate ByteBuf(9, 100)", buffer);

        // write 方法改变写指针, 写完之后写指针未到capacity的时候, buffer仍然可写
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        printByteBufInfo("writeBytes(1,2,3,4)", buffer);
        // 写指针未到capacity, buffer仍然可写, 写完int类型之后, 写指针增加4
        buffer.writeInt(12);
        printByteBufInfo("writeInt(12)", buffer);

        // 写完之后写指针等于capacity, buffer不可写
        buffer.writeBytes(new byte[]{5});
        printByteBufInfo("writeBytes(5)", buffer);

        // buffer不可写先开始扩容, 扩容之后capacity随即改变
        buffer.writeBytes(new byte[]{6});
        printByteBufInfo("writeBytes(6)", buffer);

        // get 方法不改变读写指针
        System.out.println("getByte(3) return: " + buffer.getByte(3));
        System.out.println("getShort(3) return: " + buffer.getShort(3));
        System.out.println("getInt(3) return: " + buffer.getInt(3));
        printByteBufInfo("getByte()", buffer);

        // set 方法不改变读写指针
        buffer.setByte(buffer.readableBytes() + 1, 0);
        printByteBufInfo("setByte()", buffer);

        // read方法改变读指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        printByteBufInfo("readBytes(" + dst.length + "): ", buffer);
    }
}
