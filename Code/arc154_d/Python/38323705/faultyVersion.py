def out(a, b, c):
    print('?', a, b, c, flush=True)


def inp():
    return S.index(input())


N = int(input())
S = ['No', 'Yes']
pos1 = 1
for i in range(2, N+1):
    out(i, i, pos1)
    if not inp():
        pos1 = i

P = [[i] for i in range(1, N+1)]
while len(P) > 1:
    nP = []
    for i in range(0, len(P), 2):
        if i == len(P)-1:
            nP.append(P[i])
            continue
        A = P[i]
        B = P[i+1]
        alen = len(A)
        blen = len(B)
        aind = bind = 0
        res = []
        while aind < alen or bind < blen:
            if aind == alen:
                res.append(B[bind])
                bind += 1
            elif bind == blen:
                res.append(A[aind])
                aind += 1
            else:
                out(B[bind], pos1, A[aind])
                if inp():
                    res.append(A[aind])
                    aind += 1
                else:
                    res.append(B[bind])
                    bind += 1
        nP.append(res)
    P = nP

ans = [0]*(N+1)
for i, p in enumerate(P[0]):
    ans[p] = i+1
print('!', *P[1:], flush=True)
