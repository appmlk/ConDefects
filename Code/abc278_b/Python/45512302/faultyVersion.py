#!/usr/bin/env python3

ch, cm = [int(x) for x in input().split()]
while True:
    while cm < 60:
        if (0 <= ch // 10 * 10 + cm // 10 <= 23) and (
            0 <= (ch % 10) * 10 + cm % 10 <= 59
        ):
            print(ch, cm)
            exit()
        cm += 1
    ch += 1
    cm = 0
