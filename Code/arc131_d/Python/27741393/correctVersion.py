N, M, D = map(int, input().split())
R = list(map(int, input().split()))
S = list(map(int, input().split())) + [0]
imos = [0]*D
p = (N+1)//2 * D
m = N//2 * D
imos[0] = S[0]
ind = 1
for i in range(1,N//2+1):
    if i*D > R[-1]: break
    while i*D > R[ind]: ind += 1
    imos[0] += S[ind-1]
    if i<N//2 or N%2: imos[0] += S[ind-1]
for i in range(1,M+1):
    if R[i]+1 <= p and (R[i]+1)%D!=0:
        imos[(R[i]+1)%D] += S[i]-S[i-1]
    if -R[i] >= -m:
        r = (-R[i])%D
        if r > 0:
            imos[r] += S[i-1]
            imos[r] -= S[i]
ans = score = imos[0]
for i in range(1,D):
    score += imos[i]
    ans = max(ans, score)
print(ans)