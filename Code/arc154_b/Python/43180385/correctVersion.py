N = int(input())
S = input()
T = input()

alp2num = lambda x : ord(x) - ord("a")

num_s = [0] * 26
num_t = [0] * 26

for i in range(N):
    num_s[alp2num(S[i])] += 1
    num_t[alp2num(T[i])] += 1

for i in range(26):
    if num_s[i] != num_t[i] : exit(print(-1))

cnt = 0
i = N-1
j = N

while i >= 0:
    s = S[i]
    while j > 0:
        j -= 1
        if T[j] == s : cnt += 1; break
    
    i -= 1

print(N - cnt)