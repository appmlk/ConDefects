N = int(input())

An = []
Pn = []
for _ in range(N):
    An.append(list(map(int, input().split())))
    Pn.append(list(map(int, input().split())))   

for A, P in zip(An, Pn):
    po = 0
    for i, a in enumerate(A, 1):
        po -= (i-3) * a
    if po <= 0:
        print(0)
        continue
    q, r = divmod(po, 2)
    money = min(P[3]*2, P[4])
    print(money * q +  money * r)
