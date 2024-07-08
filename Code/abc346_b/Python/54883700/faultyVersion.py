W,B = map(int,input().split())
wb = 'wbwbwwbwbwbw'
wball = wb*(200//len(wb)+4)
n = len(wball)

for i in range(n-(W+B)):
    if wball[i:(W+B+1)].count('w') == W and wball[i:(W+B+1)].count('b') == B:
        print('Yes')
        exit()
print('No')