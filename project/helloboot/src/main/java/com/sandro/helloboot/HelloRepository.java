package com.sandro.helloboot;

public interface HelloRepository {
    Hello findHello(String name);

    void increaseCount(String name);

    // 인터페이스에 default 메서드를 잘 사용하면 편하다. Comparator 인터페이스를 분석해보는 것을 추천
    default int countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }

}
