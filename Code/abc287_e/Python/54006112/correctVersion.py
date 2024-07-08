def calculate_LCP(s1, s2):
    lcp_length = 0
    min_len = min(len(s1), len(s2))
    for i in range(min_len):
        if s1[i] == s2[i]:
            lcp_length += 1
        else:
            break
    return lcp_length


N = int(input())
L = []  #String, index, LCP
for i in range(N):
    S = input()
    L.append([S, i, 0])
L = sorted(L, key=lambda x:x[0])
max_lcp = 0
for i in range(N-1):
    lcp = calculate_LCP(L[i][0], L[i+1][0])
    if lcp > max_lcp:
        L[i][2] = lcp
    else:
        L[i][2] = max_lcp
    max_lcp = lcp
L[N-1][2] = calculate_LCP(L[N-1][0], L[N-2][0])
L = sorted(L, key=lambda x:x[1])

for i in range(N):
    print(L[i][2])
