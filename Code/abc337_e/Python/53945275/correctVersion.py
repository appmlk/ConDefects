N = int(input())

# N = 8
# M = (N - 1).bitlength()
# i番目(0-indexed)の人はiビット目が立つ番号のジュースのみ飲む
# i番目の人がお腹を壊した = 腐ったワインの番号のiビット目は立っている
# 0: 1, 3, 5, 7
# 1: 2, 3, 6, 7
# 2: 4, 5, 6, 7

M = (N - 1).bit_length()
Q = [[] for _ in range(M)]
for bit in range(1, N + 1):
    for i in range(M):
        if (bit >> i) & 1:
            Q[i].append(bit)

print(M)
for i in range(M):
    print(len(Q[i]), *Q[i])

S = input()
ans = 0
for i in range(M):
    if S[i] == "1":
        ans ^= (1 << i)
print(N if ans == 0 else ans)