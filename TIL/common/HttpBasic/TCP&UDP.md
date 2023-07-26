## TCP와 UDP

### 1. 인터넷 프로토콜 스택의 4계층

TCP와 UDP 에 앞서서 인터넷 프로토콜 스택의 4계층부터 살펴보자.

![](https://yadon079.github.io/assets/img/http/http01.png)

서버에게 "Hello, world!" 라는 채팅을 보내고 싶을 때, 계층의 순서는 위에서 아래이다.

1. 애플리케이션이 "Hello, world" 메시지를 생성한다. 그러면 애플리케이션 계층 안의 Socket 라이브러리는 이 메시지를
전송 계층에 전달한다.
2. 전송 계층에서 전달 받은 메시지에 TCP나 UDP 정보를 추가하고 인터넷 계층으로 전달한다.
3. 인터넷 계층에서는 TCP와 UDP 정보가 포함된 메시지에 IP 패킷 정보를 추가한다. 그 후 네트워크 인터페이스 계층으로 전달한다.
4. 1번부터 3번의 과정을 거쳐서 데이터는 현재 **메시지 + TCP/UDP 정보 + IP 패킷 정보** 로 이루어져 있다.
이 데이터를 LAN 드라이버나 LAN 장비로 서버로 전송한다. 참고로 인터넷 계층에서 네트워크 인터페이스 계층으로 올 때 Ethernet Frame을 덮어씌운다.

이 순서를 그림으로 표현하면 다음과 같다.

![](https://yadon079.github.io/assets/img/http/http02.png)

### 2. TCP(Transmission Control Protocol) 특징

IP 특징 중에서 비연결성과 비신뢰성을 해결하기 위해 TCP가 만들어졌다. 그래서 TCP는 다음과 같은 특징을 가진다.

- 연결지향 - TCP 3 way handshake
- 데이터 전달 보증
- 순서 보장


#### 2.1 TCP 3 way handshake

TCP는 연결을 할 수 있는지 확인하기 위해 handshake 과정을 거친다.

![](http://raptor-hw.net/xe/files/attach/images/150/823/082/e1380cc4c92d9ee54eff7b0aaa627f6d.jpg)

1. 클라이언트에서 SYN 데이터를 보낸다.
2. 서버가 정상적으로 데이터를 받았을 때 다시 클라이언트에게 ACK(대답)+SYN(클라이언트도 포트를 열러달라는 요청)을 보낸다.
3. 클라이언트는 SYN + ACK을 받으면 ESTABLISHED 상태로 바뀌고 서버에게 요청을 잘 받았다는 ACK 를 전송한다.
이 ACK를 받은 서버도 ESTABLISHED 상태가 된다.

만약 서버가 요청을 제대로 받지 못하는 상황이라면 클라이언트가 SYN 요청을 보내도 서버로부터 ACK+SYN를 받을 수 없다.
따라서 TCP의 handshake 과정으로 인해 서버로 연결이 되고 있다는 것을 보증할 수 있다.
그리고 TCP는 애플리케이션 계층으로부터 받은 데이터에 순서정보, 검증정보도 추가한다. 이로 인해 TCP는 패킷의 순서도 보장한다.
그래서 TCP는 **신뢰성 있는 프로토콜**이라고 불린다.

### 3. UDP(User Datagram Protocol)

UDP는 IP와 마찬가지로 비신뢰성, 비연결성의 특징을 가진다.
IP와 다른 점은 IP 정보에 PORT와 checksum 정도만 추가되어 있다는 점이다.
handshake 과정을 거치지 않기 때문에 TCP보다 속도가 빠르다는 장점이 있다.

### 4. UDP를 어디다 써야할까?

많은 책에서 TCP와 UDP의 차이를 논할 때, TCP는 절대 잃어선 안되는 패킷을 보낼 때 사용하고
UDP는 영상을 보낼 때와같이 잃어도 상관없는 패킷일 때 사용하라고 말한다.
하지만 현재 인터넷 환경의 90% 이상은 TCP로 구성되어 있다. 영상을 보내는 패킷조차도 TCP로 구성되어 있는 경우가 많다.
그렇다면 UDP를 어디에 써야되나 싶지만 HTTP/3 이 등장하면서 UDP가 다시 주목을 받고 있다.

HTTP/3은 **QUIC**를 기반으로 돌아가는 프로토콜이고 이 QUIC는 구글이 개발한 UDP 기반의 프로토콜이다.
그리고 구글이 UDP를 선택한 이유는 오직 데이터 전송을 위해 만들어진 UDP가 각종 규약으로 묶여 있는 TCP보다 커스터마이징 하기
훨씬 쉽기 때문이다.

### 5. PORT

![](https://media.vlpt.us/images/dailyzett/post/188e9eb7-b05d-4902-8ab3-2b4882d4ce45/image.png)

그림처럼 클라이언트에서 여러 개의 애플리케이션을 실행하고 있을 때 IP 주소만으로는 애플리케이션마다 구분을 짓는 것은 불가능하다.
따라서 TCP와 UDP는 패킷 정보에 출발 PORT 정보와 목적지 PORT 정보를 추가한 뒤 이 PORT 번호로 애플리케이션을 구분한다.
포트 번호를 할당할 때는 잘 알려진 포트는 사용하지 않는 것이 좋다.

- 0 ~ 65535 : 할당 가능
- 0 ~ 1023 : 잘 알려진 포트, 사용 지양
  - FTP - 20, 21
  - TELNET - 23
  - HTTP - 80
  - HTTPS - 443

### 6. DNS(Domain Name System)

IP 주소는 외우기 어렵다는 단점이 있다. 그리고 IP 주소를 외웠다하더라도 IP 주소가 변경되면 다시 찾기 위한 고생을 해야한다.
실제로 2016년 10월 naver의 아이피 주소는 http://125.209.222.142 였지만, 22년 4월 1일인 오늘 IP 주소는 http://223.130.200.107이다.
이런 불편한 점들은 DNS를 이용해서 모두 해결할 수 있다.

클라이언트가 도메인 명인 naver.com 을 주소창에 입력하면 DNS 서버는 이름에 맞는 IP 주소를 반환해준다.
그래서 사용자는 굳이 아이피 주소를 외우지 않아도 되고, 아이피 주소가 변경되어도 도메인 주소는 동일하기 때문에 쉽게 접속할 수 있다.

### 5. 참고 자료

> (인프런) 모든 개발자를 위한 HTTP 웹 기본 지식



