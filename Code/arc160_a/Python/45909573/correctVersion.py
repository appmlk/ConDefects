N, K = map(int,input().split())
A = list(map(int,input().split()))
digit = 0
left = 1
right = N * (N+1) // 2
while digit < N:
    small = [i for i in A[digit:] if i < A[digit]]
    large = [i for i in A[digit:] if i > A[digit]]
    x = False
    if K - left < len(small):
        small.sort()
        x = small[K-left]
    elif right - K < len(large):
        large.sort()
        large.reverse()
        x = large[right-K]
    if not x:
        left += len(small)
        right -= len(large)
        digit += 1
    else:
        j = 0
        while A[j] != x:
            j += 1
        tem = A[:digit] + list(reversed(A[digit:j+1])) + A[j+1:]
        print(*tem)
        exit()
print(*A)