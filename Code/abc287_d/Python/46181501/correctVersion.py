S = input()
T = input()

# 前/後からnケタ一致する場合は front_match[n], back_match[n]
front_match = [False] * (len(T) + 1)
back_match = [False] * (len(T) + 1)
front_match[0] = True
back_match[0] = True

for idx, st in enumerate(zip(S[:len(T)], T)):
    s, t = st
    now = idx + 1
    if s == '?' or t == '?' or s == t:
        if front_match[now-1]:
            front_match[now] = True

for idx, st in enumerate(zip(reversed(S[len(S) - len(T):]), reversed(T))):
    s, t = st
    now = idx + 1
    if s == '?' or t == '?' or s == t:
        if back_match[now-1]:
            back_match[now] = True

for x in range(len(T)+1):
    if front_match[x] and back_match[len(T) - x]:
        print("Yes")
    else:
        print("No")
    