N = int(input())

if N == 3:
    ans = [[5,9,1],[3,7,8],[6,2,4]]
elif N == 4:
    ans = [[1,3,5,7],[9,11,13,15],[6,4,2,10],[8,12,14,16]]
elif N == 5:
    ans = [[1,3,5,7,9],[11,13,15,17,19],[21,23,25,8,2],[4,10,14,6,12],[16,18,20,22,24]]
else:
    odd1 = []
    odd2 = []
    evn1 = []
    evn2 = []
    for i in range(1, N*N+1):
        if i % 2 == 0:
            if i % 3 != 0:
                evn2.append(i)
            else:
                evn1.append(i)
        else:
            if i % 3 != 0:
                odd1.append(i)
            else:
                odd2.append(i)
    nums = odd1 + odd2 + evn1 + evn2
    ans = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            ans[i][j] = nums[i*N+j]

for r in ans:
    print(*r)
