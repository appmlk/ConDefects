N, A, B = map(int, input().split())
S = list(input())

res = 0
right = S.count('(') - S.count(')')
# print(right)
left = 0
for i in range(2*N):
    # print(i, 'L', left)
    if S[i] == '(':
        if left + 1 > 2 * N - i - 1:
            left -= 1
            res += 1
        else:
            left += 1
    else:
        if left == 0:
            left += 1
            res += 1
        else:
            left -= 1
tmp = 0
if right > 0:
    tmp += B * (right//2)
    res -= right//2
if A < 2 * B:
    print(A * (res//2) + B * (res % 2) + tmp)
else:
    print(B*res + tmp)
