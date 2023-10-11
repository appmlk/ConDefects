n, k = map(int, input().split())
s = input()

ans_list = []
cnt = 0
for i in range(n):
    if s[i] == 'o':
        cnt += 1
        if cnt > k:
            ans_list.append('x')
        else:
            ans_list.append('o')
    else:
        ans_list.append(s[i])
print(*ans_list, sep = '')