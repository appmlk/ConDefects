#再起回数を増やす
import sys
sys.setrecursionlimit(10 ** 8)

from collections import defaultdict
#こんな感じで使う
#Dict = defaultdict(list)

def I():
    """
    複数列、複数行の入力を縦方向にまとめint型のリストで返却する
    入力例 :
    A1 B1
    A2 B2 -> A = [1,2] B = [B1,B2]
    """
    return int(input())

def MI():
    """
    スペース区切りで入力を受け取り、mapで返却する
    入力例 : 1 2 3 4 5 -> 1,2,3,4,5
    """
    return map(int, input().split())

def LI():
    """
    スペース区切りで入力を受け取り、int型のlistで返却する
    入力例 : 1 2 3 4 5 -> [1,2,3,4,5]
    """
    return list(map(int, input().split()))

def LLI(rows_number):
    """
    # 複数行の入力を空白で区切りint型の二次元配列に変換し返却する
    入力例 : 
    1 2 3
    4 5 6 -> [[1,2,3],[4,5,6]]
    """
    if rows_number == 0:
        return [[]]
    else:
        return [list(map(int, input().split())) for _ in range(rows_number)]

def LLS(rows_number):
    """
    複数行の入力を一文字ずつ区切り二次元配列に変換し返却する
    入力例 :
    ABC
    DEF -> [[A,B,C],[D,E,F]]
    """
    if rows_number == 0:
        return [[]]
    else:
        return [list(input()) for _ in range(rows_number)]

def IH(rows_number):
    """
    一列、複数行の入力を縦方向にint型のまとめリストで返却する
    入力例 :
    A1 
    A2 -> A = [1,2] 
    """
    if rows_number == 0:
        return []
    else:
        return [int(input()) for _ in range(rows_number)]

def LH(rows_number):
    """
    複数列、複数行の入力を縦方向にまとめint型のリストで返却する
    入力例 :
    A1 B1
    A2 B2 -> A = [1,2] B = [B1,B2]
    """
    if rows_number == 0:
        return None,None
    else:
        return map(list, zip(*[map(int,input().split()) for _ in range(rows_number)]))


def return_mod(numerator, denominator, mod = 998244353):
    """
    分数のmodを返却する
    """
    try:
        inverse_denominator = pow(denominator, mod - 2, mod)
        result = (numerator * inverse_denominator) % mod
        return result
    except ZeroDivisionError:
        print("Error: Division by zero.")
    except ValueError:
        print("Error: Invalid mod value.")

def binary_search(data, value):
    """
    二部探索を行う
    """
    left = 0
    right = len(data) - 1
    while left <= right:
        mid = (left + right) // 2
        if data[mid] == value:
            return mid
        elif data[mid] < value:
            left = mid + 1
        else:
            right = mid - 1
    return -1

def binary_search_min(data, value):
    """
    二部探索を行い、value以上の最小の値を返す
    """
    left, right = 0, len(data) - 1

    # data内の全ての要素がvalueより小さい場合
    if data[right] < value:
        return None

    while left < right:
        mid = (left + right) // 2
        if data[mid] < value:
            left = mid + 1
        else:
            right = mid
    return data[left]

def binary_search_max(data, value):
    """
    二部探索を行い、value以下の最大の値を返す
    """
    left, right = 0, len(data) - 1

    # data内の全ての要素がvalueより大きい場合
    if data[left] > value:
        return None

    while left < right:
        mid = (left + right + 1) // 2  # '+1' is to ensure we do not get stuck (always favor right)
        if data[mid] > value:
            right = mid - 1
        else:
            left = mid
    return data[right]

def LUDU_to_array(LUDU):
    """
    UDUを配列に変換する
    """
    if LUDU == "L":
        return [0,-1]
    elif LUDU == "U":
        return [-1,0]
    elif LUDU == "D":
        return [1,0]
    elif LUDU == "R":
        return [0,1]
    
def sorted_indices(arr):
    """
    Aの要素を順に並び替え、並び替えた後に何番目にあるかを出力する
    """
    sorted_arr = sorted((val, idx) for idx, val in enumerate(arr))
    rank = [0] * len(arr)
    for i, (_, idx) in enumerate(sorted_arr):
        rank[idx] = i + 1
    return rank

def E(e):
    """
    enumerateの省略
    """
    return enumerate(e)

debug_Setting = False
def printn(*x):
    """
    デバック用のプリント
    提出時はdebug_SettingをFalseにする
    """
    if debug_Setting:
        print(x)

N = I()
A = LI()

LIST = [i for i in range(N+1)]

DICT = defaultdict(list)
SUM = 0
NOWLEN = 0
S = -1
X = set()
for i,a in E(A):
    if a == 1:
        NOWLEN += 1
        S = i
    else:
        while(True):
            if S != -1 and len(DICT[a-1]) > 0:
                if DICT[a-1][-1][0] not in X:
                    Y = DICT[a-1].pop()
                    if S != Y[0]:
                        Start = Y[0] + 1
                        End = i
                        while(True):
                            printn(Y[0],Start,End,X,LIST)
                            if LIST[Start] == Start:
                                if LIST[Start] < End:
                                    LIST[Start] = End - 1 
                                X.add(Start)
                                Start += 1
                            else:
                                if Start < LIST[Start]:
                                    savex = LIST[Start]
                                    LIST[Start] = End 
                                    Start = savex
                                else:
                                    while(True):
                                        pass
                            if Start >= End:
                                break
                    S = Y[0]
                    NOWLEN = Y[1]
                    break
                else:
                    DICT[a-1].pop()
            else:
                NOWLEN = 0
                S = -1 
                DICT = defaultdict(list)
                X = set()
                break
    DICT[a].append((S,NOWLEN))
    SUM += NOWLEN
    # printn(a,i,S,NOWLEN,SUM,DICT,X)
print(SUM)



# DICT = defaultdict(list)
# SUM = 0
# NOWLEN = 0
# NOUSE = set()
# for i,a in E(A):
#     if a == 1:
#         NOWLEN += 1
#         N = i
#     else:
#         while(True):
#             if len(DICT[a-1]) == 0:
#                 DICT = defaultdict(list)
#                 break
#             if DICT[a-1][-1][0] > LAST:
#                 printn(a,DICT[a-1][-1][0],LAST)
#                 DICT[a-1].pop()
#             else:
#                 break
#         if a-1 in DICT and len(DICT[a-1]) > 0 :
#             # print(type(DICT[a-1][-1]))
#             if type(DICT[a-1][-1]) == tuple:
#               LAST = DICT[a-1][-1][0]
#               NOWLEN = DICT[a-1][-1][1]
#         else:
#             NOWLEN = 0
#     SUM += NOWLEN
#     DICT[a].append((N,NOWLEN))
#     printn(a,SUM,NOWLEN,LAST,N,DICT)

# print(SUM)