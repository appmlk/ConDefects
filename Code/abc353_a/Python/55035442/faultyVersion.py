#int型で受け取るとき
N = int(input()) 

#list型で取得
l = list(map(int, input().split()))

height = l[0]

for i in range(N):
    if height < l[i]:
        print(i)
        import sys
        sys.exit()

print(-1)
