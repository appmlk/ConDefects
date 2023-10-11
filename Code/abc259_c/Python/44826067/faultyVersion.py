S = input()
T = input()
A = list()
B = list()

cnt = 0
c = ''
for s in S:
    if (cnt == 0):
        c = s
        cnt += 1
    elif (s == c):
        cnt += 1
    else:
        A.append((c,str(cnt)))
        c = s
        cnt = 1
A.append((c,str(cnt)))

cnt = 0
c = ''
for t in T:
    if (cnt == 0):
        c = t
        cnt += 1
    elif (t == c):
        cnt += 1
    else:
        B.append((c,str(cnt)))
        c = t
        cnt = 1
B.append((t,str(cnt)))

if (len(A) != len(B)):
    print('NO')
    exit()
for i in range(len(A)):
    c1 = A[i][0]
    c2 = B[i][0]
    n1 = int(A[i][1])
    n2 = int(B[i][1])
    if (c1 != c2 or n1 > n2):
        print('No')
        exit()
    if (n2 > 1 and n1 == 1):
        print('No')
        exit()
print('Yes')