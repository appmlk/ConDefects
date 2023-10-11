T = int(input())
for _ in range(T):
    A, B = map(int, input().split())
    if A > B:
        print(A-B)
        continue
    ans = 10**18
    for k in range(1, 10**7):
        if A*k > B:
            ans = min(ans, A*k-B)
            break
        d = B-A*k
        x = -(-d//k)
        
        ans = min(ans, (A+x)*k-B+x)
    print(ans)