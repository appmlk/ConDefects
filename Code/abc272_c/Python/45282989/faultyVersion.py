N = int(input())
A = list(map(int, input().split()))

even = [] #偶数
odd = [] #奇数
for i in range(N):
    
    #A[i]が偶数ならば
    if A[i]%2==0:
        even.append(A[i])
        
    #A[i]が奇数ならば
    else:
        odd.append(A[i])

even.sort(reverse=True)
odd.sort(reverse=True)

ans = -1
if len(even)>=2:
    ans = max(ans, even[0]+even[1])
elif len(odd)>=2:
    ans = max(ans, odd[0]+odd[1])

print(ans)