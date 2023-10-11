n, k = map(int,input().split())
A = list(map(int,input().split()))

seen = [-1]*n
now_pos = 0
candy = [0]
cnt = 0
while seen[now_pos] == -1:
    seen[now_pos] = cnt
    candy.append(candy[-1] + A[now_pos])
    cnt += 1
    if cnt == k:
        print(candy[-1])
        exit()
    now_pos = candy[-1] % n
start_loop = seen[now_pos]
loop_len = cnt - seen[now_pos]
start_candy = candy[start_loop]
loop_candy = candy[cnt] - start_candy

#print(seen, candy)
#print(start_loop, start_candy, loop_len, loop_candy)

ans = start_candy + (k-start_loop)//loop_len*loop_candy
rem = (k-start_loop)%loop_len
now = candy[start_loop]%n
for _ in range(rem):
    ans += A[now]
    now = ans % n

print(ans)