N = int(input())
A = list(map(int, input().split()))

count = [0 for _ in range(10**6)]
for i in range(N):
    count[A[i]] += 1

binary_list = []
binary_sum = []
for i in range(64):
    b = []
    for j in range(6):
        b.append((i>>j)&1)
        binary_list.append((i>>j)&1)
    binary_sum.append((sum(b)%2)==1)

one_to_sixty_four = list(range(1,64))

count_rect = [0 for _ in [0]*(11**6)]
powers = [11**k for k in range(6)]

for i in range(10**6):
    q = count[i]
    p = i
    b = [-1,-1,-1,-1,-1,-1]
    for j in [0,1,2,3,4,5]:
        b[j] = p%10+1
        p //= 10
    c = 0
    for k in [0,1,2,3,4,5]:
        c += b[k] * powers[k]
    for j in one_to_sixty_four:
        d = c
        for k in [0,1,2,3,4,5]:
            d -= binary_list[j*6+k]*powers[k]
        if binary_sum[j]:
            q += count_rect[d]
            #print(f"added count_rect[{d}]: {count_rect[d]}")
        else:
            q -= count_rect[d]
            #print(f"subtracted count_rect[{d}]: {count_rect[d]}")
    count_rect[c] = q
    #print(i, c, count[i], count_rect[c])


ans = 0
for a in A:
    p = a
    b = [-1,-1,-1,-1,-1,-1]
    check = True
    for j in [0,1,2,3,4,5]:
        b[j] = 10-p%10
        if b[j]<=4:
            check = False
        p //= 10
    
    c = 0
    for k in [0,1,2,3,4,5]:
        c += b[k] * powers[k]   

    #print(b, c, count_rect[c])
    ans += count_rect[c]
    if check:
        ans -= 1
print(ans//2)