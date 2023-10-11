def solve(a,b):
    sq_b = int(b**.5)+10
    ans = a
    for k in range(1,sq_b):
        tmp = (k+1)*max(0,((b-1)//k)+1-a)+k*a-b
        ans = min(ans,tmp)
    for q in range(sq_b):
        k = (b-1)//(q+1)+1
        tmp = (k+1)*max(0,((b-1)//k)+1-a)+k*a-b
        ans = min(ans,tmp)
    return ans

for _ in range(int(input())):
    a,b=map(int,input().split())
    ans = solve(a,b)
    print(ans)