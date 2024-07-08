W,B = map(int,input().split())
wb = 'wbwbwwbwbwbw'
wball = wb*(200//len(wb)+4)
n = len(wball)

for i in range(n-(W+B)):
    if wball[i:(i+W+B)].count('w') == W and wball[i:(i+W+B)].count('b') == B:
        print('Yes')
        exit()
print('No')