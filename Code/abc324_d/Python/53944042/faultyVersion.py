N = int(input())
S = input()
S = ''.join(sorted(S,reverse=True))
pos = 1
cnt = 0
while pos**2 < 10**N:
    tmp = str(pos*pos)
    tmp = ''.join(sorted(tmp,reverse=True))
    for i in range(len(tmp)):
        if tmp[i] != S[i]:
            break
    else:
        if len(tmp) < N:
            if S[len(tmp)] == '0':
                cnt += 1
        else:
            cnt +=1
    pos += 1
print(cnt)


