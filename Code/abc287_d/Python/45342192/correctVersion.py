s = input()
t = input()

head, tail = 0, 0
for i in range(len(t)):
    if s[i] != "?" and t[i] != "?" and s[i] != t[i]:
        break
    head += 1
for i in range(len(t))[::-1]:
    if s[i + len(s) - len(t)] != "?" and t[i] != "?" and s[i + len(s) - len(t)] != t[i]:
        break
    tail += 1
for i in range(len(t) + 1):
    hd = i
    tl = len(t) - i
    if hd <= head and tl <= tail:
        print("Yes")
    else:
        print("No")
