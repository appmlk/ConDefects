N = int(input())
S = input()
T = input()

diff = 0
for s, t in zip(S, T):
    if s != t:
        diff += 1
if diff % 2 == 1:
    print(-1)
    exit()
ds = diff // 2
dt = diff // 2

ans = []
for s, t in zip(S, T):
    if s == t:
        ans.append(s)
    elif ds > 0 and s == "1":
        ans.append("0")
        ds -= 1
    elif dt > 0 and t == "1":
        ans.append("0")
        dt -= 1
    elif ds > 0:
        ans.append(t)
        ds -= 1
    elif dt > 0:
        ans.append(s)
        dt -= 1
    else:
        assert False
print("".join(ans))
