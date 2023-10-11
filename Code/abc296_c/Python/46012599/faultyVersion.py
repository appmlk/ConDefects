N, X = map(int, input().split())
A = list(map(int,input().split()))
Aset = set(A)
ok = False
for a in A:
    if X-a in Aset:
        ok = True
        break
if ok:
    print('Yes')
else:
    print('No')
    