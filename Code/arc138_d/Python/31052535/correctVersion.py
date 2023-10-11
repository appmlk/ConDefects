N, K = map(int, input().split())
if (K % 2 == 0 or K == N) and not (K == N == 1):
    print("No")
else:
    print("Yes")

    z = [(1 << K) - 1]
    for i in range(K - 1):
        z.append((1 << K + 1) - 1 - (1 << i))
    for i in range(K, N):
        z.append((1 << K - 1) - 1 + (1 << i))

    ans = [0]
    a = 0
    for i in range(1, 1 << N):
        a ^= z[(i & -i).bit_length() - 1]
        ans.append(a)
    print(*ans)