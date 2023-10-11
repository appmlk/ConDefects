h, m = map(int, input().split())

def check(h, m):
    b = h % 10
    c = m // 10
    newm = (m-c*10)+b*10
    add = ((m-c*10)+b*10) // 60
    newh = (h-b)+c+add
    return 0 <= newm < 60 and 0 <= newh < 24

while True:
    if check(h, m): break
    m += 1
    h += m//60
    m %= 60
    h %= 24
print(h, m)
    
