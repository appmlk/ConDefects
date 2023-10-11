N = int(input())
P = list(map(int, input().split()))
A = list(range(1,N+1))
ans = []
B = [[] for _ in range(2)]
for i, p in enumerate(P):
    if (p - i) % 2 == 0:
        B[i % 2].append(i)
for i in range(len(B[0])):
    b0, b1 = B[0][i], B[1][i]
    if b0 > b1:
        b0, b1 = b1, b0
    while b0 + 1 < b1:
        ans.append(("B", b1 - 2))
        P[b1 - 2], P[b1] = P[b1], P[b1 - 2]
        b1 -= 2
    ans.append(("A", b0 + 1))
    P[b0], P[b0 + 1] = P[b0 + 1], P[b0]

def e_sort():
    flag = False
    for i in range(0, N - 2, 2):
        if P[i] > P[i + 2]:
            P[i], P[i + 2] = P[i + 2], P[i]
            ans.append(("B", i + 1))
            flag = True
    return flag

def o_sort():
    flag = False
    for i in range(1, N - 2, 2):
        if P[i] > P[i + 2]:
            P[i], P[i + 2] = P[i + 2], P[i]
            ans.append(("B", i + 1))
            flag = True
    return flag

os = True
es = True
while P != A:
    if es:
        es = e_sort()
    if os:
        os = o_sort()

print(len(ans))
for a in ans:
    print(*a)
