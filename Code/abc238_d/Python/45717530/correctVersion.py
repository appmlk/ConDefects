T = int(input())
a, s = zip(*[map(int, input().split(' ')) for _ in range(T)])

for i in range(T):
    A = bin(a[i])[2:]
    S = bin(s[i])[2:]
    n = len(A)
    m = len(S)

    x = a[i]
    y = a[i]
    if s[i] < 2*x:
        print("No")
        continue
    elif s[i] == 2*x:
        print("Yes")
        continue
    
    flg = True
    for j in range(m):
        if j < m-n or A[j-m+n] == "0":
            tmp = 2**(m-j-1)
            if x + y + tmp == s[i]:
                flg = False
                print("Yes")
                break
            elif x + y + tmp < s[i]:
                y += tmp
    if flg:
        print("No")