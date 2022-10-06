package com.goodee.main;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class JB04_Buffer {
	public static void main(String[] args) {
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocateDirect(100);
		
		System.out.println("저장용량 : "+buffer1.capacity() + " 바이트");
		System.out.println("저장용량 : "+buffer2.capacity() + " 바이트");
		
		//배열방식
		byte[] byte1 = new byte[100];
		byte[] byte2 = new byte[7];
		
		ByteBuffer buffer3 = ByteBuffer.wrap(byte1);
		ByteBuffer buffer4 = ByteBuffer.wrap(byte2);
		
		System.out.println("저장용량 : "+buffer3.capacity() + " 바이트");
		System.out.println("저장용량 : "+buffer4.capacity() + " 바이트");
		
		
		System.out.println("--------------데이터 제어--------------");
		
		printState(buffer4);
		
		buffer4.put((byte)10);
		buffer4.put((byte)11);
		System.out.println("[2바이트 저장 후]");
		printState(buffer4);
		
		//즉 포지션은 담을 때 실제 포지션이 괄호 안에 담아주소 한칸씩 계속 밀리는 걸 볼 수 있습니다.
		//만약에 추가로 더 복사로 코드를 넣으면
		
		buffer4.put((byte)12);
		buffer4.put((byte)13);
		buffer4.put((byte)14);
		System.out.println("[3바이트 저장 후]");
		printState(buffer4);
		//12,13,14를 추가하면 3개가 밀립니다.
		
		buffer4.flip();
		System.out.println("[filp 실행 후]");
		printState(buffer4);
		//플립을 실행하면 포지션은 위치 0으로 limit는 채워진 데이터의 끝에 위치합니다.
		
		byte[] byte3 = new byte[3];
		buffer4.get(byte3);
		System.out.println(byte3[0]+","+byte3[1]+","+byte3[2]);
		System.out.println("[3바이트 읽은 후]");
		printState(buffer4);
		
		buffer4.mark();
		System.out.println("------------[현재 위치를 마크 해놓음]");
		
		byte[] byte4 = new byte[2];
		buffer4.get(byte4);
		System.out.println(byte4[0]+","+byte4[1]);
		System.out.println("[2바이트 읽은 후]");
		printState(buffer4);
		
		buffer4.reset();
		System.out.println("----------[position을 mark 위치로 옮김]");
		printState(buffer4);
		
		buffer4.rewind();
		System.out.println("[rewind 실행 후]");
		printState(buffer4);
		
		buffer4.clear();
		System.out.println("[clear() 실행 후]");
		printState(buffer4);
		
		
	}
	
	public static void printState(Buffer buffer) {
		System.out.print("\tposition : "+buffer.position());
		System.out.print("\tlimit : "+buffer.limit());
		System.out.println("\tcapacity : "+buffer.capacity());
	}
}
