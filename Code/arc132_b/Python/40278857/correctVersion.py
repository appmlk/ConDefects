N = int(input())
P = list(map(int, input().split()))

if P[0] == 1 and P[1] == 2:
    print(0)
    exit()
if P[-1] == 1 and P[-2] == 2:
    print(1)
    exit()
if P[0] == 1 and P[1] != 2:
    print(2)
    exit()
if P[-1] == 1 and P[-2] != 2:
    print(3)
    exit()
a = P.index(1)
#print(*[i for i in range(N)])
#print(*P)
#print(a)
if P[a] + 1 == P[a + 1]:
    order = "jun"
else:
    order = "gyaku"

if order == "jun":
    plana = a
    planb = N - a + 2
else:
    plana = N - a
    planb = a + 2
print(min(plana, planb))