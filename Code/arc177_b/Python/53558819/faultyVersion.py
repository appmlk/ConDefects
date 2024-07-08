N = int(input())
S = input()
ans = ''
for i in range(N-1, -1, -1):
    s = S[i]
    if s == '1':
        print(i, 'A'*(i+1)+'B'*i)
        ans += 'A'*(i+1)+'B'*i
print(len(ans))
print(ans)
