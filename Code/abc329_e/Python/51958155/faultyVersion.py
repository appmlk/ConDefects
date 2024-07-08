# -*- coding: utf-8 -*-


def match(sub: list[str]) -> bool:
    if len(sub) != len(t):
        return False
    for e1, e2 in zip(sub, t):
        if e1 != e2 and e1 != "#":
            return False
    return True


n, m = map(int, input().split())
s = list(input())
t = input()

que: list[int] = []
for i in range(n):
    if match(s[i : i + m]):
        s[i : i + m] = ["#"] * m
        que.append(i)

checked = [False] * n
for i in que:
    if checked[i]:
        continue
    checked[i] = True
    # left side
    for j in range(1, m + 1):
        if i - j < 0:
            break
        if checked[i - j]:
            continue
        if match(s[i - i : i - j + m]):
            s[i - j : i - j + m] = ["#"] * m
            que.append(i - j)
    # right side
    for j in range(1, m + 1):
        if i + j >= n:
            break
        if checked[i + j]:
            continue
        if match(s[i + j : i + j + m]):
            s[i + j : i + j + m] = ["#"] * m
            que.append(i + j)
            checked[i + j] = True

if all(e == "#" for e in s):
    print("Yes")
else:
    print("No")
