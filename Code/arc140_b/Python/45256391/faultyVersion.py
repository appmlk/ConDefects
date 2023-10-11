import heapq
from collections import defaultdict


class MultiSet_Max():
    """
    Multi Setクラス

    Attributes
    --------------------
    add_heap : list
        追加する要素を集めたヒープキュー
    del_heap : list
        削除する要素をヒープキュー
    """
    def __init__(self):
        self.add_heapq = []
        self.del_heapq = []
        self.di = defaultdict(int)
        self.size = 0


    def add(self, x: int | float):
        """
        要素x をMulti Setに追加する
        
        Parameters
        --------------------
        x: 追加する要素
        """
        heapq.heappush(self.add_heapq, -x)
        self.di[x] += 1
        self.size += 1


    def discard(self, x: int | float):
        """
        要素x をMulti Setから削除する
        
        Parameters
        --------------------
        x: 削除する要素
        """
        heapq.heappush(self.del_heapq, -x)
        self.di[x] = max(self.di[x] - 1, 0)
        self.size -= 1


    def get_max(self):
        """
        Multi Setの最小要素を取得する
        
        Returns
        --------------------
        min_value: Multi Setの最小要素
        """
        while self.del_heapq and self.add_heapq[0] == self.del_heapq[0]:
            heapq.heappop(self.add_heapq)
            heapq.heappop(self.del_heapq)
        max_value = -self.add_heapq[0]
        return max_value


    def pop_max(self):
        """
        Multi Setの最小要素を削除する
        
        Returns
        --------------------
        min_value: Multi Setの最小要素
        """
        max_value = self.get_max()
        self.discard(max_value)
        return max_value


    def is_empty(self):
        """
        Multi Setが空かどうか判定する
        
        Returns
        --------------------
        is_empty: Multi Setが空かどうか
        """
        is_empty = len(self.add_heapq) - len(self.del_heapq) <= 0
        return is_empty

    def count(self, x: int | float):
        """
        要素xの個数を返す
        
        Returns
        --------------------
        x_count: 要素xの個数
        """
        
        x_count = self.di[x]
        return x_count

    def __len__(self):
        """
        Multi Set内にある要素の個数を返す
        
        Returns
        --------------------
        size: 要素数
        """
        return self.size


class MultiSet_Min():
    """
    Multi Setクラス

    Attributes
    --------------------
    add_heap : list
        追加する要素を集めたヒープキュー
    del_heap : list
        削除する要素をヒープキュー
    """
    def __init__(self):
        self.add_heapq = []
        self.del_heapq = []
        self.di = defaultdict(int)
        self.size = 0


    def add(self, x: int | float):
        """
        要素x をMulti Setに追加する
        
        Parameters
        --------------------
        x: 追加する要素
        """
        heapq.heappush(self.add_heapq, x)
        self.di[x] += 1
        self.size += 1


    def discard(self, x: int | float):
        """
        要素x をMulti Setから削除する
        
        Parameters
        --------------------
        x: 削除する要素
        """
        heapq.heappush(self.del_heapq, x)
        self.di[x] = max(self.di[x] - 1, 0)
        self.size -= 1


    def get_min(self):
        """
        Multi Setの最大要素を取得する
        
        Returns
        --------------------
        max_value: Multi Setの最大要素
        """
        while self.del_heapq and self.add_heapq[0] == self.del_heapq[0]:
            heapq.heappop(self.add_heapq)
            heapq.heappop(self.del_heapq)
        min_value = self.add_heapq[0]
        return min_value


    def pop_min(self):
        """
        Multi Setの最大要素を削除する
        
        Returns
        --------------------
        max_value: Multi Setの最大要素
        """
        min_value = self.get_min()
        self.discard(min_value)
        return min_value


    def is_empty(self):
        """
        Multi Setが空かどうか判定する
        
        Returns
        --------------------
        is_empty: Multi Setが空かどうか
        """
        is_empty = len(self.add_heapq) - len(self.del_heapq) <= 0
        return is_empty


    def count(self, x: int | float):
        """
        要素xの個数を返す
        
        Returns
        --------------------
        x_count: 要素xの個数
        """
        x_count = self.di[x]
        return x_count


    def __len__(self):
        """
        Multi Set内にある要素の個数を返す
        
        Returns
        --------------------
        size: 要素数
        """
        return self.size

# TODO: 
# 双方向MultiSetを使って、
# 奇数回目のときは最大値をデクリメント、
# 偶数回目のときは最小値を消去
# それの実行回数を数える。

N = int(input())
S = input()

state = S[0]
A_cnt = 1 if state == "A" else 0
C_cnt = 1 if state == "C" else 0

mset_max = MultiSet_Max()
mset_min = MultiSet_Min()

for s in S[1:]:
    if s == "A":
        if state == "A":
            A_cnt += 1
        if state == "R":
            A_cnt = 1
            C_cnt = 0
        if state == "C":
            val = min(A_cnt, C_cnt)
            if val > 0:
                mset_max.add(val)
                mset_min.add(val)
            A_cnt = 1
            C_cnt = 0
    
    if s == "R":
        if state == "A":
            pass
        if state == "R":
            A_cnt = 0
            C_cnt = 0
        if state == "C":
            val = min(A_cnt, C_cnt)
            if val > 0:
                mset_max.add(val)
                mset_min.add(val)
            A_cnt = 0
            C_cnt = 0

    if s == "C":
        if state == "A":
            A_cnt = 0
            C_cnt = 1
        if state == "R":
            C_cnt = 1
        if state == "C":
            C_cnt += 1

    state = s

val = min(A_cnt, C_cnt)
if val > 0:
    mset_max.add(val)
    mset_min.add(val)

cnt = 0
while not mset_max.is_empty() or not mset_min.is_empty():
    cnt += 1
    if cnt % 2 == 0:
        delval = mset_min.pop_min()
        mset_max.discard(delval)
    if cnt % 2 == 1:
        subval = mset_max.pop_max()
        if subval - 1 > 0:
            mset_max.add(subval - 1)
            mset_min.discard(subval)
            mset_min.add(subval)
        else:
            mset_min.discard(subval)

print(cnt)
