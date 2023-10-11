n = int(input())
A = list(map(int,input().split()))
count1 = 0
count2 = 0
for i in range(n):
    a = A[i]
    if (a == i+1):
        count1 += 1
    elif (A[a-1] == i+1):
        count2 += 1
if (count1 >= 2):
    print(count1 * (count1-1) // 2 + count2//2)
else:
    print(count2//2)