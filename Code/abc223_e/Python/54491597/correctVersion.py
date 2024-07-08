x, y, a, b, c = map(int, input().split())
def solve_two(x, y, a, b):
    if x<=0 or y<=0:
        return False
    res = ((a+x-1)//x+(b+x-1)//x <= y) or ((a+y-1)//y+(b+y-1)//y <= x)
    return res

def solve_three(x, y, a, b, c):
    case1 = solve_two(x, y-(a+x-1)//x, b, c)
    case2 = solve_two(x-(a+y-1)//y, y, b, c)
    case3 = solve_two(x, y-(b+x-1)//x, a, c)
    case4 = solve_two(x-(b+y-1)//y, y, a, c)
    case5 = solve_two(x, y-(c+x-1)//x, a, b)
    case6 = solve_two(x-(c+y-1)//y, y, a, b)
    return case1 or case2 or case3 or case4 or case5 or case6

ans = solve_three(x, y, a, b, c)
print('Yes' if ans else 'No')
