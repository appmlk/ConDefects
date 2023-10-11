T = int(input())
for i in range(T):
    x1,x2,x3 = map(int,input().split())

    #a > b > c
    a = max(x1,x2,x3)
    c = min(x1,x2,x3)
    b = x1+x2+x3-a-c

    if not( (a+b+c)%3==0 and a%2==b%2==c%2):
        print(-1)
        continue

    ans = 0
    if a - b > b - c:
        ans = (b - c) // 2 
    else:
        ans = (a - b) // 2 
    a = a + ans * 3
    b = b + ans * 5
    c = c + ans * 7

    if b == a:
        ans = ans + (b - c) // 6 * 2

    if b == c:
        ans = ans + (a - b) // 6 * 2
    
    print(ans)



    

